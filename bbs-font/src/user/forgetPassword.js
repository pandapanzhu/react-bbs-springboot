import React from 'react';
import './login.css';
import {Alert, Form, Icon, Input, Button, Checkbox } from 'antd';
import ReactDOM from 'react-dom';
import axios from 'axios';
const FormItem = Form.Item;


class ForgetPassword extends React.Component {
    constructor(props){
        super(props);
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
        if (!err) {
            console.log('Received values of form: ', values);
            var managerJson={
                email: values.email
            };
            this.submitLoginForm(managerJson);
            //提交成功之后清空数据
            this.props.form.resetFields();
        }
        });
  }

  submitLoginForm =(data) => {
      axios.post("http://localhost:8080/findPassword",data)
        .then((response) => {
            const msg = response.data.msg
             if(msg ==="success"){
                 alert("重置密码已经发送到你的邮箱，请在邮件中查收");
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
                <Form onSubmit={this.handleSubmit} className="login-form">
                    <FormItem>
                    {getFieldDecorator('email', {
                        rules: [{ required: true, message: 'Please input your email!' }],
                    })(
                        <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} type="email" placeholder="email" />
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

const ForgetPasswordForm = Form.create({})(ForgetPassword);
// ReactDOM.render(<LoginForm />, document.getElementsByClassName("login"));

export default ForgetPasswordForm;