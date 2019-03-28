import React from "react";
import {withRouter} from 'react-router-dom';
import AuthDataSource from './data/auth';
import cookie from "react-cookies";

class ActionBar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            loggedIn: typeof cookie.load("jwt") !== "undefined"
        }
        AuthDataSource.listenLoginChange(()=>{
            console.log("event!");
            this.setState({
                loggedIn: typeof cookie.load("jwt") !== "undefined"
            });
            setTimeout(()=>{
                this.navigate("/");
            },1000);
        });
    }

    navigate(path) {
        this.props.history.push(path);
    }

    render() {
        return (
            <nav className="navbar navbar-inverse">
                <a className="navbar-brand" href="/">Todo List App</a>
                <div className="float-right">
                    {!this.state.loggedIn ?
                        <button onClick={() => this.navigate('/login')}
                                className="btn btn-default navbar-btn">Login</button>
                        :
                        <button onClick={() => AuthDataSource.logout()}
                                className="btn btn-info navbar-btn">logout</button>
                    }
                </div>
            </nav>
        );
    }
}

export default withRouter(ActionBar);