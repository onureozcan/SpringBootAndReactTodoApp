import React from "react";
import {withRouter} from 'react-router-dom';

class ActionBar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            loggedIn: false
        }
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
                        <button onClick={() => this.navigate('/logout')}
                                className="btn btn-default navbar-btn">logout</button>
                    }
                </div>
            </nav>
        );
    }
}

export default withRouter(ActionBar);