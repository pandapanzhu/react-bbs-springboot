import React, { Component } from 'react';
import { Pagination } from 'antd';
import './reply.css';
import { Redirect , withRouter } from 'react-router-dom'
import axios from 'axios';

class Reply extends Component {

    constructor(props){
      super(props);
      this.state={
           reply : Object
      }
    }

    //初始化，拿到对应的数据
    componentDidMount = function () {
        const topicId = 1;
        this.state={
            replyData:[],
            replyCurrentPage :1,
            replyTotalPage :15,
        }
        this.init();
        
    };

    onReplyChange = (page) =>{
       
        //单独拿到回复信息
        this.getTopicReply(page);
    }

    getTopicReply =(page) =>{
        console.log(page);
        axios.get("http://localhost:8080/getReply/1/"+page)
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

    init(){
        this.setState({
            replyData : this.props.replyData.content,
            replyCurrentPage : this.props.replyData.numberOfElements,
            replyTotalPage : this.props.replyData.totalPages
        })
    }

    render() {
        return (
            <div className="reply">
                <h2> 回复:</h2>

                {
                    this.props.replyData.length ==0 ?"" :
                    this.props.replyData.content.map((item,i)=>{
                        const user = item.user;

                        return  <div className = "replyTop"  key={i}> 
                                    <h4 > <span className="replyAuthor" >{user.username ==null ? "楼主": user.username}  &nbsp;:&nbsp; </span> <span className="replyDate" >{item.replyDate}</span></h4>
                                    <h3 className="replyTitle">{item.text} </h3>
                                    
                                </div>
                                
                    })
                } 
                <Pagination defaultCurrent={this.props.replyData.numberOfElements} total={this.props.replyData.totalPages*10}  onChange={this.onReplyChange}/>
            </div>
        );
      }

}


export default withRouter(Reply);

