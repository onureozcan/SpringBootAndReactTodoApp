import React from "react";
import {withRouter} from "react-router-dom";
import AuthDataSource from './data/auth';

class Register extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userName: "",
            pwd: "",
            pwd2: ""
        }
    }

    navigate(path) {
        this.props.history.push(path);
    }

    doRegister() {
        if (this.state.userName && this.state.pwd1) {
            if (this.state.pwd1 === this.state.pwd2) {
                AuthDataSource.register(this.state.userName, this.state.pwd1, (args) => {
                    if (args.success) {
                        alert("success");
                    }
                });
            } else {
                alert("passwords do not match");
            }
        } else {
            alert("none of the fields should be empty!");
        }
    }

    render() {
        return (
            <div>
                <div className="panel panel-default">
                    <div className="panel-heading">Register</div>
                    <div className="panel-body">
                        <div className="input-group">
                            <input type="text" onInput={() => {
                                this.setState({
                                    "userName": event.target.value
                                });
                            }} className="form-control" placeholder="Username"/>
                            <br/><br/>
                            <input type="password" onInput={() => {
                                this.setState({
                                    "pwd1": event.target.value
                                });
                            }} className="form-control" placeholder="Password"/>
                            <br/><br/>
                            <input type="password" onInput={() => {
                                this.setState({
                                    "pwd2": event.target.value
                                });
                            }} className="form-control" placeholder="Password Again"/>
                        </div>
                        <button onClick={() => this.doRegister()}
                                className="btn btn-info">Register
                        </button>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(Register);