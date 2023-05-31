import { styled } from 'styled-components';
import { UserIcon } from '../common';
import temp from '../../assets/userImageSmall.svg';
import smile from '../../assets/smile.svg';

const Comment = () => {
  return (
    <CommentBox>
      <CommentHeader>
        <UserInfoBox>
          <UserIcon imgSrc={temp} />
          <span>chloe</span>
          <span>3분전</span>
        </UserInfoBox>
        <LabelBox>
          <img src={smile} alt="smile" />
        </LabelBox>
      </CommentHeader>
      <CommentBody>dddd</CommentBody>
    </CommentBox>
  );
};

export default Comment;

const CommentBox = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1px;

  background: ${({ theme }) => theme.color.neutralBorder};
  border: 1px solid ${({ theme }) => theme.color.neutralBorder};
  border-radius: 16px;
`;

const CommentHeader = styled.div`
  display: flex;
  justify-content: space-between;

  padding: 16px 24px;
  border-radius: 16px 16px 0 0;
  background: ${({ theme }) => theme.color.neutralBackground};
`;

const CommentBody = styled.div`
  padding: 16px 24px;
  border-radius: 0 0 16px 16px;
  background: ${({ theme }) => theme.color.neutralBackgroundStrong};
`;

const UserInfoBox = styled.div`
  display: flex;
  gap: 8px;
  align-items: center;

  span:first-of-type {
    font-size: ${({ theme }) => theme.fontSize.M.size};
    color: ${({ theme }) => theme.color.neutralTextStrong};
  }

  span:last-of-type {
    font-size: ${({ theme }) => theme.fontSize.M.size};
    color: ${({ theme }) => theme.color.neutralTextWeak};
  }
`;

const LabelBox = styled.div`
  display: flex;
  gap: 16px;
`;