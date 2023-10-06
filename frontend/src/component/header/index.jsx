import { Link } from 'react-router-dom';
import * as S from './style';
import { useState } from 'react';

export default function Header(props) {
    const [isToggled, setIsToggled] = useState(true);

    const handleToggle = () => {
        setIsToggled(!isToggled);
    };
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
                    ) : props.menuname === 'toggle' ? (
                        <S.SwitchWrapper>
                            <span>메일 구독</span> {/* "메일 구독" 텍스트 추가 */}
                            <S.Switch>
                                <input type="checkbox" checked={isToggled} onChange={handleToggle} />
                                <span className="slider round"></span>
                            </S.Switch>
                        </S.SwitchWrapper>
                    ) : null}
                </S.Sub_menu>

            </S.Title_div>
            <S.StyledHr />
        </S.Main>
    );
}
