import React from "react";
import {connect} from 'react-redux';
import {setAuthUserData} from "../../../redux/reducers/authReducer";
import {usersApi} from "../../../api/Api";
import AppHeader from "./AppHeader";

class HeaderContainer extends React.Component {
    componentDidMount() {
        usersApi.authMe()
            .then(
                data => {
                    let {id, loginName, email} = data;
                    this.props.setAuthUserData(id, loginName, email);
                }
            );
    }

    render() {
        return <AppHeader {...this.props}/>
    }
}

const mapStateToProps = (state) => ({
    isLoggedIn: state.auth.isLoggedIn,
    loginName: state.auth.loginName
});

export default connect(mapStateToProps, {setAuthUserData})(HeaderContainer);