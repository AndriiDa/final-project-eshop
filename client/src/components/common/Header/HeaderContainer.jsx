import React from 'react';
import {connect} from 'react-redux';
import {
    setLoggedInUserData,
    logout
} from '../../../redux/reducers/authReducer';
import AppHeader from './AppHeader';

class HeaderContainer extends React.Component {
    componentDidMount() {
        setLoggedInUserData();
    }
    render() {
        return <AppHeader {...this.props}/>
    }
}

const mapStateToProps = (state) => ({
    isLoggedIn: state.auth.isLoggedIn,
    loginName: state.auth.loginName
});

export default connect(mapStateToProps, {
    setLoggedInUserData,
    logout
})(HeaderContainer);