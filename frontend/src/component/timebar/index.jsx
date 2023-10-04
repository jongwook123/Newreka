import { useEffect, useState } from 'react';
import * as S from './style';

const TimeBar = ({ baseTime, setSelectedTime }) => {
    const [selectedTimeString, setSelectedTimeString] = useState('');
    const [times, setTimes] = useState([]);

    useEffect(() => {
        let date = new Date(baseTime);
        date.setHours(date.getHours() + 8);

        let tempTimes = [];

        for (let i = 0; i <= 6; i++) {
            let timeSlot = new Date(date.getTime() + (i * 10 * 60 * 1000));
            tempTimes.push(timeSlot);
        }

        setTimes(tempTimes);

        let lastTimeslot = tempTimes[tempTimes.length - 1];
        let hours = String(lastTimeslot.getHours()).padStart(2, '0');
        let minutes = String(lastTimeslot.getMinutes()).padStart(2, '0');

        setSelectedTimeString(`${hours}:${minutes}`);
    }, [baseTime]);
    console.log(times)

    return (
        <S.TimeBarContainer>
            {times.map((time) => {
                // 'HH:mm' 형식으로 시간을 표시합니다.
                let hours = String(time.getHours()).padStart(2, '0');
                let minutes = String(time.getMinutes()).padStart(2, '0');
                let timeString = `${hours}:${minutes}`;

                return (
                    <S.TimeSlot
                        key={timeString}
                        selected={selectedTimeString === timeString}
                        onClick={() => {
                            setSelectedTime(time);
                            setSelectedTimeString(timeString);
                        }}
                    >
                        {`${hours}:${minutes}`}
                    </S.TimeSlot>
                )
            })}
        </S.TimeBarContainer>
    )
};

export default TimeBar;
