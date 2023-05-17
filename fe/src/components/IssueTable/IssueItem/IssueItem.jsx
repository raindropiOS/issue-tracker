/* eslint-disable camelcase */
import styled from 'styled-components';
import React from 'react';
import CheckBox from '../CheckBox/CheckBox';
import alertCircleBlue from '../../../assets/alertCircleBlue.svg';
import mileStone from '../../../assets/mileStone.svg';
import { Label } from '../../common';

const IssueItem = ({
  number,
  title,
  created_date,
  labels,
  milestone,
  author,
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
          <p>
            #
            {number}
          </p>
          <p>
            이 이슈가
            {created_date}
            분 전,
            {author.name}
            에 의해 작성되엇습니다
          </p>
          <p>
            <img src={mileStone} alt="mileStone" />
            {milestone.name}
          </p>
        </IssueItemAttributes>
      </IssueItemBody>
      <div>작성자 이미지</div>
    </IssueItemBox>
  );
};

export default IssueItem;

const IssueItemBox = styled.li`
  display: flex;
  flex-direction: row;

  gap: 33px;
  padding: 24px;
  border-top: 1px solid #d9dbe9;

  span {
    display: flex;
    align-items: center;
  }
`;

const IssueItemBody = styled.div`
  display: flex;
  flex-direction: column;

  gap: 24px;
  flex-grow: 1;
`;

const IssueItemTitle = styled.div`
  display: flex;
  align-items: center;

  font-weight: 700;
  font-size: 18px;
  gap: 10px;
  flex-direction: row;
`;

const IssueItemAttributes = styled.div`
  display: flex;
  flex-direction: row;

  font-weight: 400;
  font-size: 16px;
  gap: 16px;
  color: #6e7191;
`;
