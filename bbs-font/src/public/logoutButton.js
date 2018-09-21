import React, { Component } from 'react';

import { Button } from 'antd';
import { Redirect , withRouter } from 'react-router-dom'

export default class LogoutButton extends Component {

    constructor(props){
        super(props);
        this.state={
            loginName : ''
        };

    }

    componentDidMount = function () {
        let items = localStorage.getItem("login");

        if(null != items || "" != items){//存在登录信息
            this.setState({
                loginName : items
            })
        }else{//不存在
            this.props.history.push('/home');
        }
    };






    render () {
        return (
            <div>
                <span>欢迎您：{this.state.loginName} </span><Button onClick={this.logoutButtonClick.bind(this)} type="danger" >退出</Button>
            </div>
        );
    }

}
