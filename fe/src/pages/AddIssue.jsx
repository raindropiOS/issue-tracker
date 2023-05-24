import { styled } from 'styled-components';
import { useState } from 'react';
import { UserIcon } from '../components/common';
import largeUserImage from '../assets/userImageLarge.svg';
import SideBar from '../components/SideBar/SideBar';
import CommentTextArea from '../components/CommentTextArea/CommentTextArea';
import TitleInput from '../components/TitleInput/TitleInput';

const AddIssue = () => {
  const [form, setForm] = useState({ title: '', comment: '' });

  const onChange = ({ target }) => {
    setForm({ ...form, [target.name]: target.value });
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
        <SideBar />
      </SectionBox>
      <ButtonBox>
        <button type="button">작성 취소</button>
        <button type="button">완료</button>
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
