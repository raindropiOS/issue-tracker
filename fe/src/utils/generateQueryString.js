import { CLOSED, OPENED } from '../constants';

export const generateQueryString = (options) => {
  const queryStringParams = [];

  const filterOptions = Object.entries(options);

  filterOptions.forEach(([key, value]) => {
    if (key === 'state') {
      if (value === OPENED) {
        queryStringParams.push('state=true');
      }
      if (value === CLOSED) {
        queryStringParams.push('state=false');
      }
    } else if (Array.isArray(value) && value.length > 0) {
      const labelNames = value.map(({ name }) => name).join(',');
      queryStringParams.push(labelNames);
    } else if (!Array.isArray(value) && value) {
      queryStringParams.push(`${key}=${value}`);
    }
  });

  return queryStringParams.length > 0 ? `?${queryStringParams.join('&')}` : '';
};
