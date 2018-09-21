import React from 'react';
import './login.css';
import {Alert, Form, Icon, Input, Button, Checkbox } from 'antd';
import ReactDOM from 'react-dom';
import axios from 'axios';
const FormItem = Form.Item;


class ResetPassword extends React.Component {
    constructor(props){
        super(props);
        this.state={
            username : ''
        }
    }


    //系统初始化
  componentDidMount = function () {
       if (undefined ===this.props.match.params) {
           
       }else{
          this.setState({
               username : this.props.match.params.id
           })
       }
    };

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
        if (!err) {
            console.log('Received values of form: ', values);
            var managerJson={
                email : values.email,
                password: values.password,
                rePassword : values.rePassword
            };
            this.submitLoginForm(managerJson);
            //提交成功之后清空数据
            this.props.form.resetFields();
        }
        });
  }

  submitLoginForm =(data) => {
      axios.post("http://localhost:8080/resetPasswordByEamil",data)
        .then((response) => {
            const msg = response.data.msg
             if(msg ==="success"){
                 alert("重置密码成功！请使用用户名密码登录");
                 this.props.history.push("/login");
             }else if(msg ==="email does noe exist"){
                alert("邮箱不存在");
             }else{
                  alert("服务器错误！请联系客服");
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
                <h2>欢迎您：{this.state.username}，请输入您的新密码:</h2>
                <Form onSubmit={this.handleSubmit} className="login-form">
                    <FormItem>
                    {getFieldDecorator('email', {
                        rules: [{ required: true, message: 'Please input your email!' }],
                    })(
                        <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="email" placeholder="email" />
                    )}
                    </FormItem>

                    <FormItem>
                    {getFieldDecorator('password', {
                        rules: [{ required: true, message: 'Please input your password!' }],
                    })(
                        <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="password" />
                    )}
                    </FormItem>
                    <FormItem>
                    {getFieldDecorator('rePassword', {
                        rules: [{ required: true, message: 'Please input your rePassword!' }],
                    })(
                        <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="rePassword" placeholder="rePassword" />
                    )}
                    </FormItem>
                    <FormItem>
                    
                    <Button type="primary" htmlType="submit" className="login-form-button">
                        提交
                    </Button><br/>
                    </FormItem>
                </Form>
            </div>
        );
      }
}

const ResetPasswordForm = Form.create({})(ResetPassword);

export default ResetPasswordForm;