import React from "react";

class TaskList extends React.Component {

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