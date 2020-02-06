import React from 'react';
import {reduxForm} from 'redux-form';
import {createField, Input} from '../../common/FormsControls/FormsControls';
import {required} from '../../../utils/validators/validators';
import {connect} from 'react-redux';
import {login} from '../../../redux/reducers/authReducer';
import {Redirect} from "react-router-dom";
import styles from "../../common/FormsControls/FormsControls.module.scss"

const LoginForm = ({handleSubmit, error}) => {
    return (
        <form onSubmit={handleSubmit}>
            {createField("Email", "email", [required], Input)}
            {createField("Password", "password", [required], Input, {type: "password"})}
            {createField(null, "rememberMe", [], Input, {type: "checkbox"}, "remember me")}

            {error && <div className={styles.formSummaryError}>
                {error}
            </div>
            }
            <div>
                <button>Login</button>
            </div>
        </form>
    )
};

const LoginReduxForm = reduxForm({form: 'login'})(LoginForm);

const Login = (props) => {
    const onSubmit = (formData) => {
        props.login(formData.email, formData.password, formData.rememberMe);
    };

    if (props.isLoggedIn && props.userId) {
        return <Redirect to={`/cart`}/>
    }

    return <div>
        <h1>Login</h1>
        <LoginReduxForm onSubmit={onSubmit} />
    </div>
};

const mapStateToProps = (state) => ({
    isLoggedIn: state.auth.isLoggedIn,
    userId: state.auth.userId
});

export default connect(mapStateToProps, {
    login
})(Login);