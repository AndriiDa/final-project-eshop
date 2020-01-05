import React, {useEffect, useState} from 'react';
import signInContentClasses from "../SignInContent/signInContent.module.scss";

const SignUpContent = (props) => {

    const initState = {
        email: '',
        firstName: '',
        lastName: '',
        loginName: '',
        loginPassword: ''
    };

    useEffect(() => {
        if (props.registerSuccess) {
            debugger
            props.closePopup()
        }
    }, [props.registerSuccess]);

    const [state, setState] = useState(initState);

    const registerBtnOnClickHandler = (e) => {
        props.userSignUp(state)
    };

    const inputOnChangeHandler = (e) => {
        const updatedState = {...state};
        updatedState[e.target.name] = e.target.value;
        setState(updatedState);
    };

    return (
        <div className={signInContentClasses.container}>
            <input placeholder={'email'}
                   type="text"
                   name={'email'}
                   value={state.email}
                   onChange={inputOnChangeHandler}/>
            <input placeholder={'firstName'}
                   type="text"
                   name={'firstName'}
                   value={state.firstName}
                   onChange={inputOnChangeHandler}/>
            <input placeholder={'lastName'}
                   type="text"
                   name={'lastName'}
                   value={state.lastName}
                   onChange={inputOnChangeHandler}/>
            <input placeholder={'loginName'}
                   type="text"
                   name={'loginName'}
                   value={state.loginName}
                   onChange={inputOnChangeHandler}/>
            <input placeholder={'loginPassword'}
                   type="text"
                   name={'loginPassword'}
                   value={state.loginPassword}
                   onChange={inputOnChangeHandler}/>
            <button onClick={registerBtnOnClickHandler}>Register</button>
        </div>
    )
};

export default SignUpContent;
