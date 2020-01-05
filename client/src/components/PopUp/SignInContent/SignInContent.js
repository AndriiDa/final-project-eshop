import React, {useEffect, useState} from 'react';
import signInContentClasses from './signInContent.module.scss'

const SignInContent = (props) => {

    const initState = {
        password: '',
        usernameOrEmail: ''
    };

    const [state, setState] = useState(initState);

    useEffect(() => {
        if (props.loginSuccess) {
            debugger
            props.closePopup()
        }
    }, [props.loginSuccess]);

    const loginBtnOnClickHandler = (e) => {
        props.userSignIn(state)
    };

    const inputOnChangeHandler = (e) => {
        const updatedState = {...state};
        updatedState[e.target.name] = e.target.value;
        setState(updatedState);
    };

    return (
        <div className={signInContentClasses.container}>
            <input placeholder={'username or email'}
                   type="text" name={'usernameOrEmail'}
                   value={state.usernameOrEmail}
                   onChange={inputOnChangeHandler}/>
            <input placeholder={'password'}
                   type="text" name={'password'}
                   value={state.password}
                   onChange={inputOnChangeHandler}/>
            <button onClick={loginBtnOnClickHandler}>Login</button>
        </div>
    )
};

export default SignInContent;
