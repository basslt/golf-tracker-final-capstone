import axios from 'axios';

export default {

  getMessageById(messageId) {
    return axios.get(`/messages/${messageId}`);
  },

  getAllMessages() {
    return axios.get('/messages');
  },

  getSentMessagesByUser(userId) {
    return axios.get(`/users/${userId}/sent-messages`);
  },

  getReceivedMessagesByUser(userId) {
    return axios.get(`/users/${userId}/received-messages`);
  },

  createMessage(message) {
    return axios.post('/messages', message);
  },

  updateMessage(messageId, message) {
    return axios.put(`/messages/${messageId}`, message);
  },

  deleteMessage(messageId) {
    return axios.delete(`/messages/${messageId}`);
  }
};
