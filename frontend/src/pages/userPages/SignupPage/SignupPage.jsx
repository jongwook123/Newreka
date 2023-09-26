import Header from 'component/header';
import * as S from './style';
import { useState } from 'react';
import LongInput1 from 'component/inputs/longinput1';
import LongButton1 from 'component/buttons/longbutton1';
import { Link } from 'react-router-dom';
import { TryLogin, TrySignup } from 'APIs/UserAPIs';

export default function SignupPage() {
    const [inputs, setInputs] = useState({
        email: "",
        name: "",
        nickname:"",
        password: "",
        pwc: "",
    });

    const onChangeHandler = (e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value,
        });
    };
    
    const buttonClickHandler = async (e) => {
        e.preventDefault();
        if (!inputs.email) {
            alert("Insert your email.");

            return;
        }

        if (!inputs.name) {
            alert("Insert your name.");

            return;
        }
        if (!inputs.nickname) {
            alert("Insert your nickname.");

            return;
        }

        if (!inputs.password) {
            alert("Insert your Password.");

            return;
        }
        if (!inputs.pwc) {
            alert("Confirm your Password.");

            return;
        }

        const regex = new RegExp(/[a-z0-9]+@[a-z]+\.[a-z]{2,3}/);

        if (!regex.test(inputs.email)) {
            alert("Invalid E-mail.");

            return;
        }

        try {
            const result = await TrySignup(inputs.email, inputs.name,inputs.nickname,inputs.password, inputs.pwc);

            if (!result.success) {
                alert("Check your E-mail or password.")
            } else {
                // dispatch(signinUser({
                //     "accessToken": result.response.accessToken,
                //     "refreshToken": result.response.refreshToken,
                // }));

                // navigate("/");
            }
        } catch (error) {
            console.log(error)
        }
    }

    return (
      <S.Main>
        <S.Header>
            <Header />
        </S.Header>
            <S.SigninForm action="">
            <LongInput1 props={{ id: "email", desc: "Insert your e-mail", color: "orange", placeholder: "Your E-mail", type: "text", value: inputs.email, callback: onChangeHandler }} />
                <LongInput1 props={{ id: "name", desc: "Insert your name", color: "blue", placeholder:"Your Name", type:"text" ,value : inputs.name ,callback :onChangeHandler}} />
                <LongInput1 props={{ id: "nickname", desc: "Insert your nickname", color: "blue", placeholder:"Your Nickname", type:"text" ,value : inputs.nickname ,callback :onChangeHandler}} />
                <LongInput1 props={{ id: "password", desc: "Insert your password", color: "blue", placeholder:"Your Password", type:"password" ,value : inputs.password ,callback :onChangeHandler}} />
                <LongInput1 props={{ id: "pwc", desc: "Insert your password", color: "blue", placeholder:"Confirm your Password", type:"password" ,value : inputs.pwc ,callback :onChangeHandler}} />
                <LongButton1 props={{color:"rgb(245, 236, 229)" ,text :"Sign up" ,callback :buttonClickHandler}}/>
            </S.SigninForm>
            <S.Footer>@SSAFY D103. All rights reserved.</S.Footer>

      </S.Main>

   )
}
