import Header from 'component/header';
import * as S from './style';
import Body1Page from './Body1Page';
import Body2Page from './Body2Page';


export default function MainPage() {
    return (
        <S.Main>
            <Header menu_name="My Page">
            </Header>
            <S.Body1>
                <h2>Hot 10</h2>
                <Body1Page/>
            </S.Body1>
            <S.Body2>
                <h2>뉴스 관련</h2>
                <Body2Page/>
            </S.Body2>
            <S.Footer>
                <p>@SSAFY D103. All rights reserved.</p>
            </S.Footer>
        </S.Main>
    )
        
}

