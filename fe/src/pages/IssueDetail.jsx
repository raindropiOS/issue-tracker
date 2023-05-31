import { styled } from 'styled-components';
import { useState } from 'react';
import CommentList from '../components/CommentList/CommentList';
import SideBar from '../components/SideBar/SideBar';
import { Button } from '../components/common';
import TitleEditInput from '../components/TitleEditInput/TitleEditInput';

const IssueDetail = () => {
  const [isEditTitle, setIsEditTitle] = useState(false);
  const [editTitleValue, setEditTitleValue] = useState('');

  const [selectedUsers, setSelectedUsers] = useState([]);
  const [selectedLabels, setSelectedLabels] = useState([]);
  const [selectedMilestone, setSelectedMilestone] = useState(null);

  const onChange = ({ target }) => setEditTitleValue(target.value);
  const toggleIsEditTitle = () => setIsEditTitle((prev) => !prev);

  const onUserClick = (user) => {
    setSelectedUsers(
      selectedUsers.some(({ id }) => id === user.id)
        ? selectedUsers.filter(({ id }) => id !== user.id)
        : [...selectedUsers, user],
    );
  };

  const onLabelClick = (label) => {
    setSelectedLabels(
      selectedLabels.some(({ id }) => id === label.id)
        ? selectedLabels.filter(({ id }) => id !== label.id)
        : [...selectedLabels, label],
    );
  };

  const onMilestoneClick = (milestone) => {
    setSelectedMilestone(
      selectedMilestone?.id === milestone.id ? null : milestone,
    );
  };

  const headerContents = isEditTitle ? (
    <>
      <HeaderBox>
        <TitleEditInput value={editTitleValue} onChange={onChange} />
      </HeaderBox>
      <ButtonsBox>
        <Button
          type="outlineButton"
          size="S"
          gap="4px"
          color="accentBorderWeak"
          onclick={toggleIsEditTitle}
        >
          편집 취소
        </Button>
        <Button
          type="containerButton"
          size="S"
          gap="4px"
          color="accentText"
          backgroundColor="accentBorderWeak"
        >
          편집 완료
        </Button>
      </ButtonsBox>
    </>
  ) : (
    <>
      <HeaderBox>
        <IssueDetailHeader>FE 이슈트래커 디자인 시스템 구현</IssueDetailHeader>
        <IssueNumberSpan>#2</IssueNumberSpan>
      </HeaderBox>
      <ButtonsBox>
        <Button
          type="outlineButton"
          size="S"
          gap="4px"
          color="accentBorderWeak"
          onclick={toggleIsEditTitle}
        >
          제목 편집
        </Button>
        <Button
          type="outlineButton"
          size="S"
          gap="4px"
          color="accentBorderWeak"
        >
          이슈 닫기
        </Button>
      </ButtonsBox>
    </>
  );

  return (
    <>
      <HeaderButtonsBox>{headerContents}</HeaderButtonsBox>
      <IssueInfoBox>
        <StatusSpan>열린 이슈</StatusSpan>
        <IssueInfoSpan>
          이 이슈가 3분 전에 sam님에 의해 열렸습니다
        </IssueInfoSpan>
        <IssueInfoSpan>코멘트 2개</IssueInfoSpan>
      </IssueInfoBox>
      <SectionBox>
        <IssueDetailBox>
          <CommentList />
          <MyButton style={{ alignSelf: 'end' }} type="button">
            + 코멘트 작성
          </MyButton>
        </IssueDetailBox>
        <SideBox>
          <SideBar
            selectedLabels={selectedLabels}
            onLabelClick={onLabelClick}
            selectedMilestone={selectedMilestone}
            onMilestoneClick={onMilestoneClick}
            selectedUsers={selectedUsers}
            onUserClick={onUserClick}
          />
          <button style={{ alignSelf: 'end' }} type="button">
            이슈 삭제
          </button>
        </SideBox>
      </SectionBox>
    </>
  );
};

export default IssueDetail;

const HeaderButtonsBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 24px;
`;

const HeaderBox = styled.div`
  flex-grow: 1;
  display: flex;
  gap: 8px;

  font-weight: 400;
  font-size: ${({ theme }) => theme.fontSize.XXL.size};
  line-height: ${({ theme }) => theme.fontSize.XXL.lineHeight};
`;

const IssueDetailHeader = styled.h1`
  color: ${({ theme }) => theme.color.neutralTextStrong};
`;

const IssueNumberSpan = styled.span`
  color: ${({ theme }) => theme.color.neutralTextWeak};
`;

const ButtonsBox = styled.div`
  display: flex;
  gap: 8px;
`;

const IssueInfoBox = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
`;

const IssueInfoSpan = styled.span`
  color: ${({ theme }) => theme.color.neutralTextWeak};
  font-size: ${({ theme }) => theme.fontSize.M.size};
  font-weight: 400;
`;

const SectionBox = styled.section`
  display: flex;
  gap: 34px;

  padding-top: 24px;
  border-top: 1px solid ${({ theme }) => theme.color.neutralBorder};
`;

const IssueDetailBox = styled.div`
  flex-grow: 3;
  display: flex;
  flex-direction: column;
  gap: 24px;
`;

const SideBox = styled.div`
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  gap: 16px;

  > button {
    color: ${({ theme }) => theme.color.dangerText};
  }
`;

// TODO: MyButton들 없애기
const MyButton = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 4px;

  padding: 0px 16px;
  width: 120px;
  height: 40px;
  background: ${({ theme }) => theme.color.accentBackground};
  border-radius: 11px;
  color: ${({ theme }) => theme.color.accentText};
  font-size: ${({ theme }) => theme.fontSize.S.size};
`;

const StatusSpan = styled.span`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 4px;

  padding: 0px 16px;
  width: 97px;
  height: 32px;
  background: ${({ theme }) => theme.color.iconBackgoundBlue};
  border-radius: 50px;
  font-size: 12px;
  color: ${({ theme }) => theme.color.accentText};
`;