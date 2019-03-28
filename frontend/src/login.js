import React from "react";
import {withRouter} from "react-router-dom";
import AuthDataSource from './data/auth';

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userName: "",
            pwd: ""
        }
    }

    navigate(path) {
        this.props.history.push(path);
    }

    doLogin() {
        if (this.state.userName && this.state.pwd) {
            AuthDataSource.login(this.state.userName, this.state.pwd, (args) => {
                if (args.success) {
                    alert("login was successful, we will redirect you in a second");
                } else {
                    alert("invalid credentials");
                }
            });
        } else {
            alert("none of the fields can be empty!");
        }
    }

    render() {
        return (
            <div>
                <div className="panel panel-default">
                    <div className="panel-heading">Please Log In</div>
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
                                    "pwd": event.target.value
                                });
                            }} className="form-control" placeholder="Password"/>
                        </div>
                        <button onClick={() => this.doLogin()}
                                className="btn btn-info">Login
                        </button>
                        <hr/>
                        <p>Do not have an account?
                            <button onClick={() => this.navigate('/register')}
                                    className="btn btn-default">Register
                            </button></p>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(Login);