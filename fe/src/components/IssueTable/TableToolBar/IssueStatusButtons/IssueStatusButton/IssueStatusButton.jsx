import styled from 'styled-components';

const IssueStatusButton = ({
  buttonText,
  buttonHandler,
  count,
  buttonStatus,
  imgSrc,
}) => {
  return (
    <IssueStatusButtonBox
      type="button"
      onClick={buttonHandler}
      $buttonstatus={buttonStatus}
    >
      <img src={imgSrc} alt={buttonText} />
      {buttonText}
      {`(${count})`}
    </IssueStatusButtonBox>
  );
};

export default IssueStatusButton;

const IssueStatusButtonBox = styled.button`
  display: flex;
  gap: 10px;

  font-size: ${({ theme }) => theme.fontSize.M.size};
  font-weight: ${({ theme }) => theme.fontWeight.bold};
  color: ${({ $buttonstatus, theme }) => ($buttonstatus ? theme.color.neutralTextStrong : theme.color.neutralText)};
  cursor: pointer;
`;
