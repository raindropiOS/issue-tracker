import { styled } from 'styled-components';
import { useEffect, useState } from 'react';
import { UserIcon } from '../components/common';
import largeUserImage from '../assets/userImageLarge.svg';
import SideBar from '../components/SideBar/SideBar';
import CommentTextArea from '../components/CommentTextArea/CommentTextArea';
import TitleInput from '../components/TitleInput/TitleInput';

const AddIssue = () => {
  const [form, setForm] = useState({ title: '', comment: '' });

  const [users, setUsers] = useState([]);
  const [labels, setLabels] = useState([]);
  const [milestones, setMilestones] = useState([]);

  const [selectedUserIds, setSelectedUserIds] = useState([]);
  const [selectedLabelIds, setSelectedLabelIds] = useState([]);
  const [selectedMilestoneId, setSelectedMilestoneId] = useState('');

  const onChange = ({ target }) => {
    setForm({ ...form, [target.name]: target.value });
  };

  const onLabelClick = (labelId) => {
    setSelectedLabelIds(
      selectedLabelIds.includes(labelId)
        ? selectedLabelIds.filter(
          (selectedLabelId) => selectedLabelId !== labelId,
        )
        : [...selectedLabelIds, labelId],
    );
  };

  const onMilestoneClick = (milestoneId) => {
    setSelectedMilestoneId(
      selectedMilestoneId === milestoneId ? '' : milestoneId,
    );
  };

  const onUserClick = (userId) => {
    setSelectedUserIds(
      selectedUserIds.includes(userId)
        ? selectedUserIds.filter((selectedUserId) => selectedUserId !== userId)
        : [...selectedUserIds, userId],
    );
  };

  // TODO: 이름 바꾸기
  // TODO: loader로 뺄지 고민해보기?
  const fetchAddIssueData = async () => {
    // TODO: url 바꾸기
    const data = await fetch('http://3.38.73.117:8080/api-fe/issues');
    const res = await data.json();

    setLabels(res.allLabels);
    setMilestones(res.allMilestones);
    setUsers(res.allUsers);
  };

  useEffect(() => {
    fetchAddIssueData();
  }, []);

  const createIssue = () => {
    fetch('http://3.38.73.117:8080/api/issues', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        title: form.title,
        contents: form.comment,
        assignees: selectedUserIds,
        milestoneName: selectedMilestoneId,
        labelNames: selectedLabelIds,
      }),
    })
      .then((res) => {
        // TODO: 에러메시지 바꾸기
        // TODO: 리다이렉션
        if (!res.ok) throw new Error('에러');
        console.log('리다이렉션으로 바꾸기');
      })
      .catch((e) => {
        // TODO: 에러처리 바꾸기
        console.log(e);
      });
  };

  return (
    <>
      <AddIssueHeader>새로운 이슈 작성</AddIssueHeader>
      <SectionBox>
        <UserIcon large imgSrc={largeUserImage} />
        <InputBox>
          <AddIssueTitleInput
            name="title"
            value={form.title}
            onChange={onChange}
            placeholder="제목"
          />
          <AddIssueCommentTextArea
            name="comment"
            value={form.comment}
            onChange={onChange}
            placeholder="코멘트를 입력하세요"
          />
        </InputBox>
        <SideBar
          labels={labels}
          selectedLabelsIds={selectedLabelIds}
          onLabelClick={onLabelClick}
          milestones={milestones}
          selectedMilestoneId={selectedMilestoneId}
          onMilestoneClick={onMilestoneClick}
          users={users}
          selectedUserIds={selectedUserIds}
          onUserClick={onUserClick}
        />
      </SectionBox>
      <ButtonBox>
        <button type="button">작성 취소</button>
        <button type="button" onClick={createIssue}>
          완료
        </button>
      </ButtonBox>
    </>
  );
};

export default AddIssue;

const AddIssueHeader = styled.h1`
  font-weight: 400;
  font-size: ${({ theme }) => theme.fontSize.XXL.size};
  line-height: ${({ theme }) => theme.fontSize.XXL.lineHeight};
  color: ${({ theme }) => theme.color.neutralTextStrong};
`;

const SectionBox = styled.div`
  display: flex;
  gap: 24px;

  border-top: 1px solid ${({ theme }) => theme.color.neutralBorder};
  border-bottom: 1px solid ${({ theme }) => theme.color.neutralBorder};
  padding: 24px 0;
`;

const InputBox = styled.div`
  flex-grow: 3;
  display: flex;
  flex-direction: column;
  gap: 8px;
  height: 500px;
`;

const AddIssueTitleInput = styled(TitleInput)`
  height: 56px;
`;

const AddIssueCommentTextArea = styled(CommentTextArea)`
  flex-grow: 1;
`;

const ButtonBox = styled.div`
  display: flex;
  justify-content: flex-end;
`;
