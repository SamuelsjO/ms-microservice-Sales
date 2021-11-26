import { MigrationInterface, QueryRunner } from 'typeorm';

export class CreateTableUsers1637103699525 implements MigrationInterface {
  public async up(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.query(
      'CREATE TABLE IF NOT EXISTS users (' +
        ' id int NOT NULL GENERATED ALWAYS AS IDENTITY ,' +
        ' name varchar(100) NOT NULL ,' +
        ' email varchar(100) NOT NULL ,' +
        ' password varchar(100) ' +
        ');',
    );
  }

  public async down(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.dropTable('users');
  }
}
