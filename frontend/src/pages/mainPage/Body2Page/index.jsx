import React, { useState } from 'react';
import * as S from './style';


export default function Body1Page() {
    const [selectedMenu, setSelectedMenu] = useState('뉴스 3줄 요약');

    const selectMenu = (menuName) => {
        setSelectedMenu(menuName);
    };

    return (
        <S.Main>
            <S.Menu>
                <S.MenuList 
                selected={selectedMenu === '뉴스 3줄 요약'} 
                onClick={() => selectMenu('뉴스 3줄 요약')}
                >
                뉴스 3줄 요약
                </S.MenuList>
                <S.MenuList 
                selected={selectedMenu === '관련 뉴스'} 
                onClick={() => selectMenu('관련 뉴스')}
                >
                관련 뉴스
                </S.MenuList>
                <S.MenuList 
                selected={selectedMenu === '문제 풀기'} 
                onClick={() => selectMenu('문제 풀기')}>
                    문제 풀기</S.MenuList>
            </S.Menu>
            <S.Title>Selected Keyword</S.Title>
            <S.MenuBody>

            </S.MenuBody>
        </S.Main>
    )
}
