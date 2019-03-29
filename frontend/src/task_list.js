import React from "react";
import TaskListDataSource from './data/task_list'

class TaskList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            list: [],
            searchParameter: ""
        };
    }

    componentDidMount() {
        this.getTasks();
    }

    getTasks() {
        TaskListDataSource.listTaskLists((args) => {
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

    navigate(path) {
        this.props.history.push(path);
    }

    addNewTaskList() {
        let taskListName = window.prompt("Task List Name");
        if (taskListName) {
            TaskListDataSource.addList(taskListName, (args) => {
                if (args.success) {
                    this.getTasks();
                } else {
                    alert(args.data);
                }
            });
        }
    }

    deleteTaskList(taskId) {
        if (window.confirm("are you sure? there will be no rollback")) {
            TaskListDataSource.deleteList(taskId, (args) => {
                if (args.success) {
                    this.getTasks();
                } else {
                    alert(args.data);
                }
            });
        }
    }

    render() {
        const taskLists = [];
        for (let i = 0; i < this.state.list.length; i++) {
            let item = this.state.list[i];
            taskLists.push(
                <tr key={i}>
                    <td><a href={"/task?id="+item.id}>{item.name}</a></td>
                    <td>
                        <button className="btn btn-danger" onClick={() => {
                            this.deleteTaskList(item.id);
                        }}>delete
                        </button>
                    </td>
                </tr>
            );
        }

        return (
            <div>
                <div>
                    <div className="col-sm-11">
                        <h1 className="no-margin">Tasks Lists</h1>
                    </div>
                    <div className="col-sm-1">
                        <button className="btn btn-success" onClick={() => {
                            this.addNewTaskList()
                        }}>+
                        </button>
                    </div>
                </div>
                <div className="col-sm-12 margin-top-10">
                    <div className="input-group">
                        <input type="text" className="form-control" onInput={() => {
                            this.setState({
                                searchParameter: event.target.value
                            });
                        }} placeholder="Search for..."/>
                        <span className="input-group-btn">
                        <button className="btn btn-primary no-margin" onClick={()=>this.getTasks()} type="button">Search!</button>
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
                                            <th>Options</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {taskLists}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            : <div className="alert alert-info">You have no task lists yet</div>
                    }
                </div>
            </div>
        );
    }

}

export default TaskList;