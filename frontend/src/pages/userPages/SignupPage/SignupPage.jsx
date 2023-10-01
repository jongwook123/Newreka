import Header from 'component/header';
import * as S from './style';
import { useState } from 'react';
import LongInput1 from 'component/inputs/longinput1';
import LongButton1 from 'component/buttons/longbutton1';
import { TrySignup } from 'APIs/UserAPIs';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router';
import { signinUser } from 'redux/slice/userSlice';

export default function SignupPage() {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [inputs, setInputs] = useState({
        email: "",
        name: "",
        nickname:"",
        password: "",
        pwck: "",
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
        if (!inputs.pwck) {
            alert("Confirm your Password.");

            return;
        }

        try {
            const result = await TrySignup(inputs.email, inputs.name, inputs.nickname, inputs.password, inputs.pwck);
            if (result.msg !== "Success signup") {
                alert("result.error.message")
            } else {
                navigate("/login");
            }
            
            // 성공 시 다른 작업을 수행할 수 있습니다.
        } catch (error) {
            // 회원 가입 실패 시 에러 처리
            console.error('Signup failed:', error);
        }
    }

    return (
      <S.Main>
        <S.Header>
            <Header />
        </S.Header>
            <S.SigninForm action="">
            <LongInput1 props={{ id: "email", desc: "Insert your e-mail", color: "orange", placeholder: "Your E-mail", type: "email", value: inputs.email, callback: onChangeHandler }} />
                <LongInput1 props={{ id: "name", desc: "Insert your name", color: "blue", placeholder:"Your Name", type:"text" ,value : inputs.name ,callback :onChangeHandler}} />
                <LongInput1 props={{ id: "nickname", desc: "Insert your nickname", color: "blue", placeholder:"Your Nickname", type:"text" ,value : inputs.nickname ,callback :onChangeHandler}} />
                <LongInput1 props={{ id: "password", desc: "Insert your password", color: "blue", placeholder:"Your Password", type:"password" ,value : inputs.password ,callback :onChangeHandler}} />
                <LongInput1 props={{ id: "pwck", desc: "Insert your password", color: "blue", placeholder:"Confirm your Password", type:"password" ,value : inputs.pwck ,callback :onChangeHandler}} />
                <LongButton1 props={{color:"rgb(245, 236, 229)" ,text :"Sign up" ,callback :buttonClickHandler}}/>
            </S.SigninForm>
            <S.Footer>@SSAFY D103. All rights reserved.</S.Footer>

      </S.Main>

   )
}
