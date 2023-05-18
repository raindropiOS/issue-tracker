/* eslint-disable camelcase */
import styled from 'styled-components';
import React from 'react';
import CheckBox from '../CheckBox/CheckBox';
import alertCircleBlue from '../../../assets/alertCircleBlue.svg';
import mileStone from '../../../assets/mileStone.svg';
import { Label, UserIcon } from '../../common';
import userImageLarge from '../../../assets/userImageLarge.svg';

const IssueItem = ({
  number, title, createdDate, labels, milestone, user,
}) => {
  return (
    // TODO: Label key 수정 필요
    // TODO: 템플릿 리터럴 이용
    <IssueItemBox>
      <CheckBox />
      <IssueItemBody>
        <IssueItemTitle>
          <img src={alertCircleBlue} alt="alertCirlceBlue" />
          <p>{title}</p>
          {labels.map((labelInfo) => (
            <Label key={labelInfo.name} {...labelInfo} />
          ))}
        </IssueItemTitle>
        <IssueItemAttributes>
          <span>
            #
            {number}
          </span>
          <span>
            {`이 이슈가 ${createdDate} 분 전, ${user.nickname}에 의해 작성되었습니다.`}
          </span>
          <span>
            <img src={mileStone} alt="mileStone" />
            {milestone.name}
          </span>
        </IssueItemAttributes>
      </IssueItemBody>
      <div>
        <UserIcon imgSrc={userImageLarge} />
      </div>
    </IssueItemBox>
  );
};

export default IssueItem;

const IssueItemBox = styled.li`
  display: flex;
  align-items: center;
  flex-direction: row;

  padding: 24px;
  background: ${({ theme }) => theme.color.neutralBackgroundStrong};

  > div {
    padding: 0 33px;
  }
`;

const IssueItemBody = styled.div`
  display: flex;
  flex-direction: column;

  gap: 8px;
  flex-grow: 1;
`;

const IssueItemTitle = styled.div`
  display: flex;
  align-items: center;

  font-size: ${({ theme }) => theme.fontSize.L.size};
  font-weight: ${({ theme }) => theme.fontWeight.bold};
  color: ${({ theme }) => theme.color.neutralTextStrong};
  gap: 10px;
  flex-direction: row;
`;

const IssueItemAttributes = styled.div`
  display: flex;
  flex-direction: row;
  gap: 16px;

  font-size: ${({ theme }) => theme.fontSize.M.size};
  font-weight: ${({ theme }) => theme.fontWeight.regular};
  color: ${({ theme }) => theme.color.neutralTextWeak};

  > span {
    display: flex;
    gap: 8px;
  }
`;
