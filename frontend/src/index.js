import React from 'react'
import ReactDOM from 'react-dom'
import {Route, Link, BrowserRouter as Router} from 'react-router-dom'

import TaskList from './task_list'
import Task from './task'
import Login from './login'
import Register from './register'
import ActionBar from './action_bar'

const routing = (
    <Router>
        <div>
            <ActionBar/>
        </div>
        <div className="">
            <div className="container">
                <Route exact path="/" component={TaskList}/>
                <Route exact path="/task" component={Task}/>
                <Route path="/login" component={Login}/>
                <Route path="/register" component={Register}/></div>
        </div>
    </Router>
);


ReactDOM.render(
    routing,
    document.getElementById('app')
);

module.hot.accept();