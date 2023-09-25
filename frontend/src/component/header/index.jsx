import * as S from './style';

export default function Header() {

    return (
        <S.Main>
            <S.Date>April 26, 2023</S.Date>
            <S.Title_div>
                <S.Dummy>
                    
                </S.Dummy>
                <S.Title>NewReka</S.Title>
                {/* Login 후 */}
                <S.Sub_menu>
                    My page
                </S.Sub_menu>
                {/* Login 전 */}
                
            </S.Title_div>
        </S.Main>
    );
}
 