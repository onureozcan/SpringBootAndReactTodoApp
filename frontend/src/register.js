import React from "react";
import {withRouter} from "react-router-dom";

class Register extends React.Component {

    navigate(path) {
        this.props.history.push(path);
    }

    doRegister(){

    }

    render() {
        return (
            <div>
                <div className="panel panel-default">
                    <div className="panel-heading">Register</div>
                    <div className="panel-body">
                        <div className="input-group">
                            <input type="text" className="form-control" placeholder="Username"/>
                            <br/><br/>
                            <input type="password" className="form-control" placeholder="Password"/>
                            <br/><br/>
                            <input type="password" className="form-control" placeholder="Password Again"/>
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