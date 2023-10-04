import { useEffect, useState } from 'react';
import * as S from './style';

const TimeBar = ({ formattedTime, setSelectedTime }) => {
    const [selectedTimeString, setSelectedTimeString] = useState('');
    const [times, setTimes] = useState([]);

    useEffect(() => {
        if (formattedTime) {
            const year = parseInt(formattedTime.substring(0, 4));
            const month = parseInt(formattedTime.substring(4, 6)) - 1; // JS에서 월은 0부터 시작합니다.
            const day = parseInt(formattedTime.substring(6, 8));
            const hour = parseInt(formattedTime.substring(8, 10));
            const minute = parseInt(formattedTime.substring(10));

            let date = new Date(year, month, day, hour-1, minute);

            let tempTimes = [];

            for (let i = 0; i <= 6; i++) {
                let timeSlot = new Date(date.getTime() + (i * 10 * 60 * 1000));
                tempTimes.push(timeSlot);
            }

            setTimes(tempTimes);

            let lastTimeslot = tempTimes[tempTimes.length - 1];

            let hoursString = String(lastTimeslot.getHours()).padStart(2, '0');
            let minutesString = String(lastTimeslot.getMinutes()).padStart(2, '0');

            setSelectedTimeString(`${hoursString}:${minutesString}`);
        }
    }, [formattedTime]);


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
