import React from 'react';
import './login.css';
import {Alert, Form, Icon, Input, Button, Checkbox } from 'antd';
import ReactDOM from 'react-dom';
import axios from 'axios';
import Header from '../public/Header';
const FormItem = Form.Item;


class Login extends React.Component {
    constructor(props){
        super(props);
        this.state={
            isLogin :false
        }
    }

    componentDidMount = function () {
        this.isLogin();
    };

    //判断是否登录
    isLogin(){
        axios.get("http://localhost:8080/isLogin")
        .then((response) => {
            const data = response.data
             console.log(data);
             if(null != data && data.msg==="success"){//已登录
                const html = "欢迎您，"+data.data.username;
                alert(html);
             }else{
                 //没登录
             }
             

        }).catch( (error) =>{
            console.log(error);
        })

    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
        if (!err) {
            console.log('Received values of form: ', values);
            var managerJson={
                username: values.userName,
                password: values.password,
            };
            this.submitLoginForm(managerJson);
            //提交成功之后清空数据
            this.props.form.resetFields();
        }
        });
  }

  submitLoginForm =(data) => {
      axios.post("http://localhost:8080/loginUser",data)
        .then((response) => {
            const msg = response.data.msg
             if(msg =="success"){
                 alert("登录成功！");
                 localStorage.setItem("login",response.data.login.username);

                //  this.isLogin();
                 this.setState({
                    isLogin : true
                 })
                 this.props.history.push("/home");
             }else{
                 alert("登录失败");
             }
                         

        }).catch( (error) =>{
            console.log(error);
        })
  }


    renderMessage = content => (
        <Alert style={{ marginBottom: 24 }} message={content} type="error" showIcon />
      );

     render() {
         const { getFieldDecorator } = this.props.form;
         return (
             
            <div className="login">
                <Form onSubmit={this.handleSubmit} className="login-form">
                    <FormItem>
                    {getFieldDecorator('userName', {
                        rules: [{ required: true, message: 'Please input your username!' }],
                    })(
                        <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username" />
                    )}
                    </FormItem>
                    <FormItem>
                    {getFieldDecorator('password', {
                        rules: [{ required: true, message: 'Please input your Password!' }],
                    })(
                        <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="Password" />
                    )}
                    </FormItem>
                    <FormItem>
                    {getFieldDecorator('remember', {
                        valuePropName: 'checked',
                        initialValue: true,
                    })(
                        <Checkbox>Remember me</Checkbox>
                    )}
                    <a className="login-form-forgot" href="/forgetPassword">Forgot password</a><br/>
                    <Button type="primary" htmlType="submit" className="login-form-button">
                        Log in
                    </Button><br/>
                    Or <a href="/register">register now!</a>
                    </FormItem>
                </Form>
            </div>
        );
      }
}

const LoginForm = Form.create({})(Login);
// ReactDOM.render(<LoginForm />, document.getElementsByClassName("login"));

export default LoginForm;