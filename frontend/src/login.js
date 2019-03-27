import React from "react";
import {withRouter} from "react-router-dom";

class Login extends React.Component {

    navigate(path) {
        this.props.history.push(path);
    }

    doLogin(){

    }

    render() {
        return (
            <div>
                <div className="panel panel-default">
                    <div className="panel-heading">Please Log In</div>
                    <div className="panel-body">
                        <div className="input-group">
                            <input type="text" className="form-control" placeholder="Username"/>
                            <br/><br/>
                            <input type="password" className="form-control" placeholder="Password"/>
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