import { MigrationInterface, QueryRunner } from 'typeorm';

export class CreateTableAuth1637103699525 implements MigrationInterface {
  public async up(queryRunner: QueryRunner): Promise<void> {
    console.log('aqui na table Auth');
    await queryRunner.query(
      'CREATE TABLE IF NOT EXISTS Auth (' +
        ' id int NOT NULL GENERATED ALWAYS AS IDENTITY ,' +
        ' email varchar(100) NOT NULL ,' +
        ' password varchar(100) ' +
        ');',
    );
  }

  public async down(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.dropTable('Auth');
  }
}
