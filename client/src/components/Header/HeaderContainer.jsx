import React from "react";
import Header from "./Header";
import {connect} from 'react-redux';
import {setAuthUserData} from "../../store/reducers/auth.reducer";
import {usersApi} from "../../api/Api";

class HeaderContainer extends React.Component {
    // componentDidMount() {
    //     usersApi.authMe()
    //         .then(
    //             data => {
    //                 let {id, loginName, email} = data;
    //                 this.props.setAuthUserData(id, loginName, email);
    //             }
    //         );
    // }

    render() {
        return <Header {...this.props}/>
    }
}

const mapStateToProps = (state) => ({
    isLoggedIn: state.auth.isLoggedIn,
    loginName: state.auth.loginName
});

export default connect(mapStateToProps, {setAuthUserData})(HeaderContainer);
