import React from "react";
import Header from "./Header";
import {connect} from 'react-redux';
import * as axios from "axios";
import {setAuthUserData} from "../../redux/reducers/authReducer";

class HeaderContainer extends React.Component {
    componentDidMount() {
        axios.get(`http://localhost:9000/api/v1/users/me`, {
            // withCredentials: true
            headers: {
                Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTc3NjQ0NDgyLCJleHAiOjE1NzgyNDkyODJ9.PKWADEvW6U-PlWtNuZVI2-4kO_iDLKpAAOJuXipFk5kCit5gU4z4t9FSV9oQhX6E1h4IMceFYnQtMmiZbmmiqw"
            }
        }).then(
            response => {
                let {id, loginName, email } = response.data;
                this.props.setAuthUserData(id, loginName, email);
            }
        );
    }

    render() {
        return <Header {...this.props}/>
    }
}

const mapStateToProps = (state) => ({
    isLoggedIn: state.auth.isLoggedIn,
    loginName: state.auth.loginName
});

export default connect(mapStateToProps, {setAuthUserData})(HeaderContainer);