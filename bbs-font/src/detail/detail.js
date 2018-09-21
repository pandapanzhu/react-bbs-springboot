import React, { Component } from 'react';
import axios from 'axios';
import './detail.css';
import Reply from './reply';
import { Pagination } from 'antd';

class Detail extends Component {

    constructor(props){
      super(props);
      this.topicId =1;
      this.state={
           topic : Object,
           reply : []
      }
    }

  //系统初始化
  componentDidMount = function () {
      if (undefined ===this.props.match.params) {
          this.topicId = 1;
       }else{
          this.topicId = this.props.match.params.id;
       }
        this.getTopic(this.topicId,1);
    };

    onReplyChange = (page) =>{
        this.getTopic(this.topicId,page);
    }

    getTopic =(topicId,pageNum) =>{
        axios.get("http://localhost:8080/getTopic/"+topicId+"/"+pageNum)
        .then((response) => {
            const data = response.data;
            if(data.msg="success"){
                this.setState({
                    topic : data.data,
                    reply : data.reply
                });
            }
        }).catch( (error) =>{
            console.log(error);
        })
    }

                // <Reply  replyData = {this.state.reply}/>
    render(){
        const topic = this.state.topic;
        const user = topic.user;
        return (
            <div className="TopicDetail">
                <div >
                    <h1> {this.state.topic.topicName}</h1>
                    <p className="subTitle" ><span>作者：</span><span>
                    {
                        user != undefined ? user.username : ""
                    }</span> <span>&nbsp;|&nbsp;</span><span>发布时间：</span><span>{this.state.topic.publicDate}</span></p>
                    <p className="content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  {this.state.topic.text} </p>
                
                </div>

                <div className="reply">
                <h2> 回复:</h2>

                {
                    this.state.reply.length ==0 ?"" :
                    this.state.reply.content.map((item,i)=>{
                        const user = item.user;

                        return  <div className = "replyTop"  key={i}> 
                                    <h4 > <span className="replyAuthor" >{user.username ==null ? "楼主": user.username}  &nbsp;:&nbsp; </span> <span className="replyDate" >{item.replyDate}</span></h4>
                                    <h3 className="replyTitle">{item.text} </h3>
                                    
                                </div>
                                
                    })
                } 
                <Pagination defaultCurrent={this.state.reply.numberOfElements} total={this.state.reply.totalPages*10}  onChange={this.onReplyChange}/>
            </div>


        </div>
        )
    }

}

export default Detail;