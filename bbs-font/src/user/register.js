import React from 'react';
import './login.css';
import {Alert, Form, Icon, Input, Button, Checkbox } from 'antd';
import ReactDOM from 'react-dom';
import axios from 'axios';
const FormItem = Form.Item;


class Register extends React.Component {
    constructor(props){
        super(props);
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
        if (!err) {
            console.log('Received values of form: ', values);
            var managerJson={
                username : values.userName,
                password : values.password,
                email    : values.email
            };
            this.submitRegisterForm(managerJson);
            //提交成功之后清空数据
            this.props.form.resetFields();
        }
        });
  }

  submitRegisterForm =(data) => {
      axios.post("http://localhost:8080/register",data)
        .then((response) => {
            const msg = response.data.msg
             if(msg =="success"){
                 alert("注册成功！");
                 localStorage.setItem("login",response.data.login.username);
                 this.props.history.push("/home");
             }else{
                 alert("注册失败:"+msg);
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
                    {getFieldDecorator('email', {
                        rules: [{ required: true, message: 'Please input your email!' }],
                    })(
                        <Input prefix={<Icon type="e-mail" style={{ color: 'rgba(0,0,0,.25)' }} />} type="email" placeholder="email" />
                    )}
                    </FormItem>

                    <FormItem>
                    <Button type="primary" htmlType="submit" className="login-form-button">
                        注册
                    </Button><br/>
                        已有账号？ <a href="/login">立即登录!</a>
                    </FormItem>
                </Form>
            </div>
        );
      }
}

const RegisterForm = Form.create({})(Register);
// ReactDOM.render(<LoginForm />, document.getElementsByClassName("login"));

export default RegisterForm;