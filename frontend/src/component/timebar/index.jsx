import * as S from './style';
import { useState } from 'react';

const TimeBar = ({ baseTime }) => {
    const [selectedTime, setSelectedTime] = useState(null);

    let date = new Date(baseTime);
    date.setHours(date.getHours() + 8);

    let times = [];

    for (let i = 0; i <= 6; i++) {
        let timeSlot = new Date(date.getTime() + (i * 10 * 60 * 1000));
        times.push(timeSlot);

    }

    return (
        <S.TimeBarContainer>
            {times.map((time) => {
                // 'HH:mm' 형식으로 시간을 표시합니다.
                let hours = String(time.getHours()).padStart(2, '0');
                let minutes = String(time.getMinutes()).padStart(2, '0');
                let timeString = `${hours}:${minutes}`;

                return (
                    <S.TimeSlot key={time} selected={selectedTime === timeString} onClick={() => setSelectedTime(timeString)}>
                        {`${hours}:${minutes}`}
                    </S.TimeSlot>
                )
            })}
        </S.TimeBarContainer>
    )
};

export default TimeBar;

