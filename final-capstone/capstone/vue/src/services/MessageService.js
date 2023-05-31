import axios from 'axios';

export default {
  getMessageById(messageId) {
    return axios.get(`/messages/${messageId}`)
      .then(response => response.data)
      .catch(error => {
        if (error.response && error.response.status === 404) {
          throw new Error('Message not found');
        }
        throw new Error('Failed to fetch message');
      });
  },

  getAllMessages() {
    return axios.get('/messages')
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch messages');
      });
  },

  getSentMessagesByUser(userId) {
    return axios.get(`/users/${userId}/sent-messages`)
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch sent messages');
      });
  },

  getReceivedMessagesByUser(userId) {
    return axios.get(`/users/${userId}/received-messages`)
      .then(response => response.data)
      .catch(Error => {
        throw new Error('Failed to fetch received messages');
      });
  },

  createMessage(message) {
    return axios.post('/messages', message)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to create message');
      });
  },

  updateMessage(messageId, message) {
    return axios.put(`/messages/${messageId}`, message)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to update message');
      });
  },

  deleteMessage(messageId) {
    return axios.delete(`/messages/${messageId}`)
      .then(() => {})
      .catch(Error => {
        throw new Error('Failed to delete message');
      });
  }
};
