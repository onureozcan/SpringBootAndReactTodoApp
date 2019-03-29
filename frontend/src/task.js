import React from "react";
import TaskDataSource from "./data/task";

class Task extends React.Component {

    taskListId = "";

    constructor(props) {
        super(props);
        this.state = {
            isNewTaskPageVisible: false,
            list: [],
            newTaskName: "",
            newTaskDescription: "",
            newTaskDueDate: "",
            searchParameter: ""
        };
    }

    componentDidMount() {
        this.taskListId = window.location.href.split("?id=")[1];
        console.log("task list id:" + this.taskListId);
        this.getTasks();
    }

    getTasks() {
        console.log("list tasks:" + this.taskListId);
        TaskDataSource.listTasks(parseInt(this.taskListId), (args) => {
            if (args.success) {
                if (this.state.searchParameter != "") {
                    args.data = args.data
                        .filter((x)=>x.name.indexOf(this.state.searchParameter)!= -1);
                }
                this.setState({
                    list: args.data
                })
            }
        });
    }

    getSearch(){
        this.getTasks();
    }

    addNewTask() {
        if (this.state.newTaskName
            && this.state.newTaskDescription
            && this.state.newTaskDueDate) {
            TaskDataSource.addNewTask(
                this.taskListId,
                this.state.newTaskName,
                this.state.newTaskDescription,
                this.state.newTaskDueDate,
                (args) => {
                    if (args.success) {
                        this.getTasks();
                    } else {
                        alert(args.data);
                    }
                }
            );
        } else {
            alert("no field is allowed to be empty");
        }
    }

    deleteTask(taskId) {
        if (window.confirm("Are you sure deleting this item?")) {
            TaskDataSource.delete(taskId, (args) => {
                if (args.success) {
                    this.getTasks();
                } else {
                    alert(args.data);
                }
            });
        }
    }

    renderNewTaskPage() {
        if (this.state.isNewTaskPageVisible) {
            return <div>
                <div className="panel panel-default">
                    <div className="panel-heading">
                        <h5>Add New Item</h5>
                    </div>
                    <div className="panel-body">
                        <input className="form-control" onInput={() => {
                            this.setState({
                                newTaskName: event.target.value
                            });
                        }} placeholder="Task Name"/>
                        <br/>
                        <input className="form-control" onInput={() => {
                            this.setState({
                                newTaskDescription: event.target.value
                            });
                        }} placeholder="Description"/>
                        <br/>
                        <input className="form-control" type="date" onInput={() => {
                            this.setState({
                                newTaskDueDate: new Date(event.target.value).getTime()
                            });
                        }} placeholder="Due Date"/>
                        <hr/>
                        <div className="col-sm-12 text-align-right">
                            <button className="btn btn-default" onClick={() => {
                                this.setState({
                                    isNewTaskPageVisible: false
                                });
                            }}>Cancel
                            </button>
                            <button className="btn btn-success" onClick={() => {
                                this.addNewTask();
                            }}>Add
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        }
        return null;
    }

    format(current_datetime) {
        let formatted_date = current_datetime.getDate() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getFullYear()
        return formatted_date;
    }

    completeTask(taskId) {
        TaskDataSource.complete(taskId, (args) => {
            if (args.success) {
                this.getTasks();
            } else {
                alert(args.data);
            }
        });
    }

    render() {
        const tasks = [];
        for (let i = 0; i < this.state.list.length; i++) {
            let item = this.state.list[i];
            tasks.push(
                <tr key={i}>
                    <td>{item.name}</td>
                    <td>
                        <div style={{"max-width": "200px"}}>
                            {item.description}</div>
                    </td>
                    <td>{this.format(new Date(item.dueDate)) + ""}</td>
                    <td>{item.status == 1 ? "COMPLETED" : item.dueDate > new Date().getTime() ? "OPEN" : "EXPIRED"}</td>
                    <td>{(item.dependsOn || {name: "no dependency"}).name}</td>
                    <td>
                        <button className="btn btn-danger" onClick={() => {
                            this.deleteTask(item.id);
                        }}>delete
                        </button>
                        {
                            item.status == 1 ? <span></span> :
                                <button className="btn btn-primary" onClick={() => {
                                    this.completeTask(item.id);
                                }}>complete
                                </button>
                        }
                        <button className="btn btn-default" onClick={() => {
                            this.dependency(item.id);
                        }}>add dependency
                        </button>
                    </td>
                </tr>
            );
        }
        return (
            <div>
                {this.renderNewTaskPage()}
                <div>
                    <div className="col-sm-11">
                        <h1 className="no-margin">Tasks</h1>
                    </div>
                    <div className="col-sm-1">
                        <button className="btn btn-success" onClick={() => {
                            this.setState({
                                isNewTaskPageVisible: true
                            });
                        }}>+
                        </button>
                    </div>
                </div>
                <div className="col-sm-12 margin-top-10">
                    <div className="input-group">
                        <input type="text" className="form-control"
                               onInput={() => {
                                   this.setState({
                                       searchParameter: event.target.value
                                   });
                               }}
                               placeholder="Search for..."/>
                        <span className="input-group-btn">
                        <button className="btn btn-primary no-margin"
                                onClick={()=>this.getTasks()} type="button">Search!</button>
                    </span>
                    </div>
                </div>
                <div className="col-sm-12 margin-top-10">
                    {
                        this.state.list.length > 0 ?
                            <div className="pnl pnl-default">
                                <div className="pnl-body">
                                    <table className="table">
                                        <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Due Date</th>
                                            <th>Status</th>
                                            <th>Depends On</th>
                                            <th>Options</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {tasks}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            : <div className="alert alert-info">You have no tasks in this list yet</div>
                    }
                </div>
            </div>
        );
    }
}

export default Task;