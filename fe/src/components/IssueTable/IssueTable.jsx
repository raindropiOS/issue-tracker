import styled from 'styled-components';
import CheckBox from '../CheckBox';
import IssueItem from './IssueItem/IssueItem';
import IssueStatusButtons from './TableToolBar/IssueStatusButtons/IssueStatusButtons';
import TableFilterButtons from './TableToolBar/TableFilterButtons/TableFilterButtons';
import TableToolBar from './TableToolBar/TableToolBar';

const IssueTable = ({ issues, ...rest }) => {
  const issueList = issues.length ? (
    issues.map((issue) => <IssueItem key={issue.number} {...issue} />)
  ) : (
    <NoticeBox>검색과 일치하는 결과가 없습니다.</NoticeBox>
  );

  return (
    <IssueTableBox>
      <TableToolBar>
        <CheckBox />
        <IssueStatusButtons {...rest} />
        <TableFilterButtons />
      </TableToolBar>
      {issueList}
    </IssueTableBox>
  );
};

export default IssueTable;

const IssueTableBox = styled.div`
  display: flex;
  flex-direction: column;

  height: auto;
  border: 1px solid #d9dbe9;
  border-radius: 16px;
`;

const NoticeBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  height: 100px;
  font-weight: 400;
  font-size: 16px;
  color: #6e7191;
`;
