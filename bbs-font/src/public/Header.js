import React, { Component } from 'react';
import './Header.css';
import axios from 'axios';
import simpletour from './simpletour.png';
import { Redirect , withRouter } from 'react-router-dom'
import { Button } from 'antd';
import LoginButton from './loginButton';
import LogoutButton from './logoutButton';


class Header extends Component {
    constructor(props){
        super(props);
        this.state={
            plateList : [],
            plateName : '',
            loginNmae: ''
        };

    }

    componentDidMount = function () {
        this.getPlate();
        
        
    };

    

    getPlate(){
        axios.get("http://localhost:8080/getPlate")
        .then((response) => {
            const list = response.data.data
             this.setState({
                plateList:list
             });
             

        }).catch( (error) =>{
            console.log(error);
        })

    }

    
    //返回主页的方法
    backToIndexClick=(e) =>{
        this.props.history.push("/home/1");
    }

    

    plateClick =(plateName) => {
        if(plateName){
            //点击后修改背景颜色
            document.getElementsByName(plateName);

            this.setState({
                plateName : plateName
            },function(){//拿到板块id之后，通过板块的id拿到对应的topic
                // this.props.router.push({ pathname:'/home',state:{plateId : plateName } )
                this.props.history.push("/blank/"+plateName);
            })
        }
    }

        //退出的button
    logoutButtonClick = (e)=>{
        localStorage.setItem("login","");
        this.props.history.push("/login");
    }

    //登录的button
    loginButtonClick = (e)=>{
        this.props.history.push("/login");
    }

    //注册界面
    registerButtonClick =(e) =>{
        this.props.history.push("/register");
    }

    

    render () {
        var plateList = this.state.plateList;
        this.items = localStorage.getItem("login");
        console.log(this.items);
        
        return (
            <div>
                <div className="App-header" >
                    <img src={simpletour} className="App-logo" alt="logo" onClick={this.backToIndexClick.bind(this)} />
                    <span className="title-text">简途论坛</span>
                    <div className="rightButton">
                        {
                            this.items == "" ? 
                            <div>
                                <Button onClick = {this.loginButtonClick.bind(this)} type="primary">登录</Button><Button onClick={this.registerButtonClick.bind(this)} type="danger" >注册</Button>
                            </div>
                            : 
                            <div>
                                <span>欢迎您：{this.items} </span><Button onClick={this.logoutButtonClick.bind(this)} type="danger" >退出</Button>
                            </div>
                        }
                    </div>
                </div>
                <div>
                    <p className="plateTitle" >
                        <span> 板块分类：</span> 
                        {
                            this.state.plateList.map((item)=>{
                            return <span className="plate" onClick={this.plateClick.bind(this,item.id)} key={item.id}>|{item.plateName}|</span>
                            })
                        }
                    </p>
                </div>
            </div>
        );
    }
}

export default withRouter(Header);