import React from "react";

class Task extends React.Component {

    navigate(path) {
        this.props.history.push(path);
    }

    render() {
        return (
            <div>
                <h1>Tasks</h1>
            </div>
        );
    }
}

export default Task;