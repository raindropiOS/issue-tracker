import styled from 'styled-components';
import FilterBar from './FilterBar/FilterBar';
import NabTab from './NavTab/NabTab';

const TabBar = ({ filterOptions }) => {
  return (
    <TabBarBox>
      <FilterBar filterOptions={filterOptions} />
      <NabTab />
    </TabBarBox>
  );
};

export default TabBar;

const TabBarBox = styled.div`
  display: flex;
  justify-content: space-between;
`;
