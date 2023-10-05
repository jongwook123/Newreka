import { GetCountKeyword } from 'APIs/KeywordAPIs';
import * as S from './style';
import {
    Chart as ChartJS,
    RadialLinearScale,
    ArcElement,
    Tooltip,
    Legend,
} from 'chart.js';
import { useEffect } from 'react';
import { PolarArea } from 'react-chartjs-2';
import { useSelector } from 'react-redux';
import { useState } from 'react';

ChartJS.register(RadialLinearScale, ArcElement, Tooltip, Legend);




export default function Keywords() {
    const accessToken = useSelector((state) => state.user.accessToken);
    const [keywords, setKeywords] = useState({
        오피니언:0,
        세계:0,
        생활:0,
        사회:0,
        경제:0,
        정치:0,
        IT:0,
    })
    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await GetCountKeyword(accessToken);
                setKeywords({
                    오피니언: result.keyWordList["오피니언"],
                    세계: result.keyWordList["세계"],
                    생활: result.keyWordList["생활"],
                    사회: result.keyWordList["사회"],
                    경제: result.keyWordList["경제"],
                    정치: result.keyWordList["정치"],
                    IT: result.keyWordList["IT"],
                });
            } catch (error) {
                console.error('Error fetching keyword count:', error);
            }
        };

        fetchData();
    }, [accessToken]);

    const data = {
        labels: ['오피니언', '세계', '생활', '사회', '경제', '정치', 'IT'],
        datasets: [
            {
                label: '',
                data: [
                    keywords["오피니언"], 
                    keywords["세계"], 
                    keywords["생활"], 
                    keywords["사회"], 
                    keywords["경제"], 
                    keywords["정치"], 
                    keywords["IT"], 
                    ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 206, 86, 0.5)',
                    'rgba(75, 192, 192, 0.5)',
                    'rgba(153, 102, 255, 0.5)',
                    'rgba(255, 4, 64, 0.5)',
                    'rgba(255, 159, 64, 0.5)',
                ],
                borderWidth: 1,
            },
        ],
    };
    
    
    const options = {
        plugins: {
            legend: {
                position: 'bottom',  // Place legend below the chart
            },
        },
    };

    return (
        <S.Main>
            <PolarArea data={data} options={options}/>
        </S.Main>
    )
}
