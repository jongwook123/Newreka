import Header from 'component/header';
import * as S from './style';
import { useState } from 'react';
import LongInput1 from 'component/inputs/longinput1';
import LongButton1 from 'component/buttons/longbutton1';
import { Link } from 'react-router-dom';

export default function LoginPage() {
    const [inputs, setInputs] = useState({
        id: "",
        password: "",
    });

    const onChangeHandler = (e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value,
        });
    };
    
    const buttonClickHandler = async (e) => {
        e.preventDefault();
        
        if (!inputs.id) {
            alert("Insert your email.");
            return;
        }

        if (!inputs.password) {
            alert("Insert your Password.");
            return;
        }
    }

    return (
      <S.Main>
          <S.Header><Header /></S.Header>
          <S.Title>Login</S.Title>
          <S.SigninForm action="">
              <LongInput1 props={{ id: "id", desc: "Insert your e-mail", color: "orange", placeholder: "Your E-mail", type: "text", value: inputs.id, callback: onChangeHandler }} />
              <LongInput1 props={{ id: "password", desc: "Insert your password", color: "blue", placeholder:"Your Password", type:"password" ,value : inputs.password ,callback :onChangeHandler}} />
              <S.TextWrapper>
                  Don't have an account? 
                  <Link to='/signup'>Sign up</Link> or 
                  <Link to='/findpassword'>Find Password</Link>
              </S.TextWrapper>
              <LongButton1 props={{color:"green" ,text :"Sign in" ,callback :buttonClickHandler}}/>
          </S.SigninForm>

          <S.Footer>@SSAFY D103. All rights reserved.</S.Footer>

      </S.Main>

   )
}
