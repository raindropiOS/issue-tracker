import {
  SET_STATE_FILTER_OPTION,
  SET_WRITTEN_BY_ME_FILTER_OPTION,
  SET_ASSIGNED_TO_ME_FILTER_OPTION,
  SET_COMMENTED_BY_ME_FILTER_OPTION,
  SET_ASSIGNEE_FILTER_OPTION,
  SET_LABELS_FILTER_OPTION,
  SET_MILESTONE_FILTER_OPTION,
  SET_AUTHOR_FILTER_OPTION,
  SET_PAGE_FILTER_OPTION,
  SET_USERS,
  SET_LABELS,
  SET_MILESTONES,
  SET_MAIN_PAGE_STATE,
  FETCH_START,
  FETCH_SUCCESS,
  FETCH_ERROR,
  RESET_FILTER_OPTIONS,
} from './MainPageActionTypes';

export const setMainPageState = (state) => ({
  type: SET_MAIN_PAGE_STATE,
  payload: state,
});

export const fetchStart = () => ({
  type: FETCH_START,
});

export const fetchSuccess = () => ({
  type: FETCH_SUCCESS,
});

export const fetchError = () => ({
  type: FETCH_ERROR,
});

export const resetFilterOptions = (filterOptions) => ({
  type: RESET_FILTER_OPTIONS,
  payload: filterOptions,
});

export const setStateFilterOption = (state) => ({
  type: SET_STATE_FILTER_OPTION,
  payload: state,
});

export const setWrittenByMeFilterOption = (writtenByMe) => ({
  type: SET_WRITTEN_BY_ME_FILTER_OPTION,
  payload: writtenByMe,
});

export const setAssignedToMeFilterOption = (assignedToMe) => ({
  type: SET_ASSIGNED_TO_ME_FILTER_OPTION,
  payload: assignedToMe,
});

export const setCommentedByMeFilterOption = (commentedByMe) => ({
  type: SET_COMMENTED_BY_ME_FILTER_OPTION,
  payload: commentedByMe,
});

export const setAssigneeFilterOption = (assignee) => ({
  type: SET_ASSIGNEE_FILTER_OPTION,
  payload: assignee,
});

export const setLabelsFilterOption = (labels) => ({
  type: SET_LABELS_FILTER_OPTION,
  payload: labels,
});

export const setMilestoneFilterOption = (milestone) => ({
  type: SET_MILESTONE_FILTER_OPTION,
  payload: milestone,
});

export const setAuthorFilterOption = (author) => ({
  type: SET_AUTHOR_FILTER_OPTION,
  payload: author,
});

export const setPageFilterOption = (page) => ({
  type: SET_PAGE_FILTER_OPTION,
  payload: page,
});

export const setUsers = (users) => ({
  type: SET_USERS,
  payload: users,
});

export const setLabels = (labels) => ({
  type: SET_LABELS,
  payload: labels,
});

export const setMilestones = (milestones) => ({
  type: SET_MILESTONES,
  payload: milestones,
});
