
import WordCloudPage from './WordCloud';
import * as S from './style';


export default function Body1Page() {
    return (
        <S.Main>
            <S.Title>
                HOT 10
            </S.Title>
            <S.Body>
                <WordCloudPage/>
            </S.Body>
        </S.Main>
    )
        
}