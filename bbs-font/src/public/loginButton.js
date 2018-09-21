import React, { Component } from 'react';

import { Button } from 'antd';
import { Redirect , withRouter } from 'react-router-dom'

export default  class LoginButton extends Component {

    

    //注册界面
    registerButtonClick =(e) =>{
        this.props.history.push("/register");
    }


    render () {
        return (
            <div className="LoginButton">
                
            </div>
        );
    }

}