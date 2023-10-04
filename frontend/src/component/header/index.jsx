import { Link } from 'react-router-dom';
import * as S from './style';

export default function Header(props) {

    return (
        <S.Main>
            <S.Date>{new Date().toLocaleDateString()}</S.Date>
            <S.Title_div>
                <S.Dummy>
                    
                </S.Dummy>
                <S.Title><Link to='/'>NewReka</Link></S.Title>
                <S.Sub_menu>
                {props.menuname === 'My page' ? (
                        <Link to='/mypage'>My Page</Link>
                    ) : props.menuname === 'Login' ? (
                        <Link to='/login'>Login</Link>
                    ) : null}
                </S.Sub_menu>
                
            </S.Title_div>
        </S.Main>
    );
}
 