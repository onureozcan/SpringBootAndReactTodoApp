import React from "react";
import TaskListDataSource from './data/task_list'

class TaskList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tasks: []
        };
    }

    componentDidMount() {
        TaskListDataSource.listTasks((args) => {
            if (args.success) {
                this.setState({
                    tasks: args.data
                })
            }
        });
    }

    navigate(path) {
        this.props.history.push(path);
    }

    render() {
        return (
            <div>
                <h1>Tasks Lists</h1>
            </div>
        );
    }
}

export default TaskList;