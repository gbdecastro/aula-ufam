describe('Employee - CRUD', () => {
  it('Visits the initial project page', () => {
    cy.visit('/');
    cy.contains('app is running!');
  });
});
